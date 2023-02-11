package fr.frostbreker.onetwenty.entity.custom.camel;

import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Dynamic;
import fr.frostbreker.onetwenty.entity.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class Camel extends AbstractHorse implements PlayerRideableJumping, RiderShieldingMount, Saddleable {
    public static final Ingredient TEMPTATION_ITEM = Ingredient.of(Items.CACTUS);
    public static final int DASH_COOLDOWN_TICKS = 55;
    private static final float RUNNING_SPEED_BONUS = 0.1F;
    private static final float DASH_VERTICAL_MOMENTUM = 1.4285F;
    private static final float DASH_HORIZONTAL_MOMENTUM = 22.2222F;
    private static final int SITDOWN_DURATION_TICKS = 40;
    private static final int STANDUP_DURATION_TICKS = 52;
    private static final int IDLE_MINIMAL_DURATION_TICKS = 80;
    private static final float SITTING_HEIGHT_DIFFERENCE = 1.43F;
    public static final EntityDataAccessor<Boolean> DASH = SynchedEntityData.defineId(Camel.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Long> LAST_POSE_CHANGE_TICK = SynchedEntityData.defineId(Camel.class, EntityDataSerializers.LONG);
    public final AnimationState walkAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();
    public final AnimationState sitPoseAnimationState = new AnimationState();
    public final AnimationState sitUpAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState dashAnimationState = new AnimationState();
    private static final EntityDimensions SITTING_DIMENSIONS = EntityDimensions.scalable(EntityType.CAMEL.getWidth(), ModEntityTypes.CAMEL.get().getHeight() - 1.43F);
    private int dashCooldown = 0;
    private int idleAnimationTimeout = 0;

    public Camel(EntityType<? extends Camel> p_248516_, Level p_249295_) {
        super(p_248516_, p_249295_);
        this.maxUpStep = 1.5F;
        GroundPathNavigation groundpathnavigation = (GroundPathNavigation)this.getNavigation();
        groundpathnavigation.setCanFloat(true);
        groundpathnavigation.setCanWalkOverFences(true);
    }

    public void addAdditionalSaveData(CompoundTag p_250330_) {
        super.addAdditionalSaveData(p_250330_);
        p_250330_.putBoolean("IsSitting", this.getPose() == Pose.SITTING);
        p_250330_.putLong("LastPoseTick", this.entityData.get(LAST_POSE_CHANGE_TICK));
    }

    public void readAdditionalSaveData(CompoundTag p_250781_) {
        super.readAdditionalSaveData(p_250781_);
        if (p_250781_.getBoolean("IsSitting")) {
            this.setPose(Pose.SITTING);
        }

        this.resetLastPoseChangeTick(p_250781_.getLong("LastPoseTick"));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createBaseHorseAttributes().add(Attributes.MAX_HEALTH, 32.0D).add(Attributes.MOVEMENT_SPEED, (double)0.09F).add(Attributes.JUMP_STRENGTH, (double)0.42F);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DASH, false);
        this.entityData.define(LAST_POSE_CHANGE_TICK, -52L);
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_249190_, DifficultyInstance p_251264_, MobSpawnType p_250254_, @Nullable SpawnGroupData p_249259_, @Nullable CompoundTag p_251838_) {
        CamelAI.initMemories(this, p_249190_.getRandom());
        this.entityData.set(LAST_POSE_CHANGE_TICK, p_249190_.getLevel().getGameTime() - 52L);
        return super.finalizeSpawn(p_249190_, p_251264_, p_250254_, p_249259_, p_251838_);
    }

    protected Brain.Provider<Camel> brainProvider() {
        return CamelAI.brainProvider();
    }

    protected void registerGoals() {
    }

    protected Brain<?> makeBrain(Dynamic<?> p_251586_) {
        return CamelAI.makeBrain(this.brainProvider().makeBrain(p_251586_));
    }

    public EntityDimensions getDimensions(Pose p_248973_) {
        return p_248973_ == Pose.SITTING ? SITTING_DIMENSIONS.scale(this.getScale()) : super.getDimensions(p_248973_);
    }

    protected float getStandingEyeHeight(Pose p_248908_, EntityDimensions p_250490_) {
        return p_250490_.height - 0.1F;
    }

    public double getRiderShieldingHeight() {
        return 0.5D;
    }

    protected void customServerAiStep() {
        this.level.getProfiler().push("camelBrain");
        Brain<?> brain = this.getBrain();
        ((Brain<Camel>)brain).tick((ServerLevel)this.level, this);
        this.level.getProfiler().pop();
        this.level.getProfiler().push("camelActivityUpdate");
        CamelAI.updateActivity(this);
        this.level.getProfiler().pop();
        super.customServerAiStep();
    }

    public void tick() {
        super.tick();
        if (this.isDashing() && this.dashCooldown < 55 && (this.onGround || this.isInWater())) {
            this.setDashing(false);
        }

        if (this.dashCooldown > 0) {
            --this.dashCooldown;
            if (this.dashCooldown == 0) {
                this.level.playSound((Player)null, this.blockPosition(), SoundEvents.CAMEL_DASH_READY, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        }

        if (this.level.isClientSide()) {
            this.setupAnimationStates();
        }

    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        switch (this.getPose()) {
            case STANDING:
                this.sitAnimationState.stop();
                this.sitPoseAnimationState.stop();
                this.dashAnimationState.animateWhen(this.isDashing(), this.tickCount);
                this.sitUpAnimationState.animateWhen(this.isInPoseTransition(), this.tickCount);
                this.walkAnimationState.animateWhen((this.onGround || this.hasControllingPassenger()) && this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D, this.tickCount);
                break;
            case SITTING:
                this.walkAnimationState.stop();
                this.sitUpAnimationState.stop();
                this.dashAnimationState.stop();
                if (this.isSittingDown()) {
                    this.sitAnimationState.startIfStopped(this.tickCount);
                    this.sitPoseAnimationState.stop();
                } else {
                    this.sitAnimationState.stop();
                    this.sitPoseAnimationState.startIfStopped(this.tickCount);
                }
                break;
            default:
                this.walkAnimationState.stop();
                this.sitAnimationState.stop();
                this.sitPoseAnimationState.stop();
                this.sitUpAnimationState.stop();
                this.dashAnimationState.stop();
        }

    }

    public void travel(Vec3 p_250068_) {
        if (this.isAlive()) {
            if (this.refuseToMove() && this.isOnGround()) {
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.0D, 1.0D, 0.0D));
                p_250068_ = p_250068_.multiply(0.0D, 1.0D, 0.0D);
            }

            super.travel(p_250068_);
        }
    }

    public boolean refuseToMove() {
        return this.isPoseSitting() || this.isInPoseTransition();
    }

    protected float getDrivenMovementSpeed(LivingEntity p_252019_) {
        float f = p_252019_.isSprinting() && this.getJumpCooldown() == 0 ? 0.1F : 0.0F;
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED) + f;
    }

    protected boolean mountIgnoresControllerInput(LivingEntity p_252088_) {
        boolean flag = this.isInPoseTransition();
        if (this.isPoseSitting() && !flag && p_252088_.zza > 0.0F) {
            this.standUp();
        }

        return this.refuseToMove() || super.mountIgnoresControllerInput(p_252088_);
    }

    public boolean canJump(Player p_259848_) {
        return !this.refuseToMove() && this.getControllingPassenger() == p_259848_ && super.canJump(p_259848_);
    }

    public void onPlayerJump(int p_249138_) {
        if (this.isSaddled() && this.dashCooldown <= 0 && this.isOnGround()) {
            super.onPlayerJump(p_249138_);
        }
    }

    protected void executeRidersJump(float p_251967_, float p_249345_, float p_249119_) {
        double d0 = this.getAttributeValue(Attributes.JUMP_STRENGTH) * (double)this.getBlockJumpFactor() + this.getJumpBoostPower();
        this.addDeltaMovement(this.getLookAngle().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double)(22.2222F * p_251967_) * this.getAttributeValue(Attributes.MOVEMENT_SPEED) * (double)this.getBlockSpeedFactor()).add(0.0D, (double)(1.4285F * p_251967_) * d0, 0.0D));
        this.dashCooldown = 55;
        this.setDashing(true);
        this.hasImpulse = true;
    }

    public boolean isDashing() {
        return this.entityData.get(DASH);
    }

    public void setDashing(boolean p_251380_) {
        this.entityData.set(DASH, p_251380_);
    }

    public boolean isPanicking() {
        return this.getBrain().checkMemory(MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_PRESENT);
    }

    public void handleStartJump(int p_249968_) {
        this.playSound(SoundEvents.CAMEL_DASH, 1.0F, 1.0F);
        this.setDashing(true);
    }

    public void handleStopJump() {
    }

    public int getJumpCooldown() {
        return this.dashCooldown;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.CAMEL_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.CAMEL_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_250052_) {
        return SoundEvents.CAMEL_HURT;
    }

    protected void playStepSound(BlockPos p_252056_, BlockState p_251457_) {
        if (p_251457_.getSoundType() == SoundType.SAND) {
            this.playSound(SoundEvents.CAMEL_STEP_SAND, 1.0F, 1.0F);
        } else {
            this.playSound(SoundEvents.CAMEL_STEP, 1.0F, 1.0F);
        }

    }

    public boolean isFood(ItemStack p_248671_) {
        return TEMPTATION_ITEM.test(p_248671_);
    }

    public InteractionResult mobInteract(Player p_249032_, InteractionHand p_251004_) {
        ItemStack itemstack = p_249032_.getItemInHand(p_251004_);
        if (p_249032_.isSecondaryUseActive()) {
            this.openCustomInventoryScreen(p_249032_);
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else {
            InteractionResult interactionresult = itemstack.interactLivingEntity(p_249032_, this, p_251004_);
            if (interactionresult.consumesAction()) {
                return interactionresult;
            } else if (this.isFood(itemstack)) {
                return this.fedFood(p_249032_, itemstack);
            } else {
                if (this.getPassengers().size() < 2 && !this.isBaby()) {
                    this.doPlayerRide(p_249032_);
                }

                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
        }
    }

    protected void onLeashDistance(float p_251143_) {
        if (p_251143_ > 6.0F && this.isPoseSitting() && !this.isInPoseTransition()) {
            this.standUp();
        }

    }

    protected boolean handleEating(Player p_249923_, ItemStack p_248995_) {
        if (!this.isFood(p_248995_)) {
            return false;
        } else {
            boolean flag = this.getHealth() < this.getMaxHealth();
            if (flag) {
                this.heal(2.0F);
            }

            boolean flag1 = this.isTamed() && this.getAge() == 0 && this.canFallInLove();
            if (flag1) {
                this.setInLove(p_249923_);
            }

            boolean flag2 = this.isBaby();
            if (flag2) {
                this.level.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), 0.0D, 0.0D, 0.0D);
                if (!this.level.isClientSide) {
                    this.ageUp(10);
                }
            }

            if (!flag && !flag1 && !flag2) {
                return false;
            } else {
                if (!this.isSilent()) {
                    SoundEvent soundevent = this.getEatingSound();
                    if (soundevent != null) {
                        this.level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), soundevent, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
                    }
                }

                return true;
            }
        }
    }

    protected boolean canPerformRearing() {
        return false;
    }

    public boolean canMate(Animal p_251650_) {
        if (p_251650_ != this && p_251650_ instanceof Camel camel) {
            if (this.canParent() && camel.canParent()) {
                return true;
            }
        }

        return false;
    }

    @Nullable
    public Camel getBreedOffspring(ServerLevel p_251227_, AgeableMob p_251047_) {
      return ModEntityTypes.CAMEL.get().create(p_251227_);
    }

    @Nullable
    protected SoundEvent getEatingSound() {
        return SoundEvents.CAMEL_EAT;
    }

    protected void actuallyHurt(DamageSource p_250410_, float p_251451_) {
        this.standUpPanic();
        super.actuallyHurt(p_250410_, p_251451_);
    }

    public void positionRider(Entity p_251173_) {
        int i = this.getPassengers().indexOf(p_251173_);
        if (i >= 0) {
            boolean flag = i == 0;
            float f = 0.5F;
            float f1 = (float)(this.isRemoved() ? (double)0.01F : this.getBodyAnchorAnimationYOffset(flag, 0.0F) + p_251173_.getMyRidingOffset());
            if (this.getPassengers().size() > 1) {
                if (!flag) {
                    f = -0.7F;
                }

                if (p_251173_ instanceof Animal) {
                    f += 0.2F;
                }
            }

            Vec3 vec3 = (new Vec3(0.0D, 0.0D, (double)f)).yRot(-this.yBodyRot * ((float)Math.PI / 180F));
            p_251173_.setPos(this.getX() + vec3.x, this.getY() + (double)f1, this.getZ() + vec3.z);
            this.clampRotation(p_251173_);
        }
    }

    private double getBodyAnchorAnimationYOffset(boolean p_249228_, float p_251763_) {
        double d0 = this.getPassengersRidingOffset();
        float f = this.getScale() * 1.43F;
        float f1 = f - this.getScale() * 0.2F;
        float f2 = f - f1;
        boolean flag = this.isInPoseTransition();
        boolean flag1 = this.getPose() == Pose.SITTING;
        if (flag) {
            int i = flag1 ? 40 : 52;
            int j;
            float f3;
            if (flag1) {
                j = 28;
                f3 = p_249228_ ? 0.5F : 0.1F;
            } else {
                j = p_249228_ ? 24 : 32;
                f3 = p_249228_ ? 0.6F : 0.35F;
            }

            float f4 = (float)this.getPoseTime() + p_251763_;
            boolean flag2 = f4 < (float)j;
            float f5 = flag2 ? f4 / (float)j : (f4 - (float)j) / (float)(i - j);
            float f6 = f - f3 * f1;
            d0 += flag1 ? (double) Mth.lerp(f5, flag2 ? f : f6, flag2 ? f6 : f2) : (double)Mth.lerp(f5, flag2 ? f2 - f : f2 - f6, flag2 ? f2 - f6 : 0.0F);
        }

        if (flag1 && !flag) {
            d0 += (double)f2;
        }

        return d0;
    }

    public Vec3 getLeashOffset(float p_251477_) {
        return new Vec3(0.0D, this.getBodyAnchorAnimationYOffset(true, p_251477_) - (double)(0.2F * this.getScale()), (double)(this.getBbWidth() * 0.56F));
    }

    public double getPassengersRidingOffset() {
        return (double)(this.getDimensions(this.getPose()).height - (this.isBaby() ? 0.35F : 0.6F));
    }

    public void onPassengerTurned(Entity p_250747_) {
        if (this.getControllingPassenger() != p_250747_) {
            this.clampRotation(p_250747_);
        }

    }

    private void clampRotation(Entity p_252070_) {
        p_252070_.setYBodyRot(this.getYRot());
        float f = p_252070_.getYRot();
        float f1 = Mth.wrapDegrees(f - this.getYRot());
        float f2 = Mth.clamp(f1, -160.0F, 160.0F);
        p_252070_.yRotO += f2 - f1;
        float f3 = f + f2 - f1;
        p_252070_.setYRot(f3);
        p_252070_.setYHeadRot(f3);
    }

    protected boolean canAddPassenger(Entity p_248594_) {
        return this.getPassengers().size() <= 2;
    }

    @Nullable
    public LivingEntity getControllingPassenger() {
        if (!this.getPassengers().isEmpty() && this.isSaddled()) {
            Entity entity = this.getPassengers().get(0);
            if (entity instanceof LivingEntity) {
                return (LivingEntity)entity;
            }
        }

        return null;
    }

    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }

    public boolean isPoseSitting() {
        return this.getPose() == Pose.SITTING;
    }

    public boolean isInPoseTransition() {
        long i = this.getPoseTime();
        boolean flag;
        switch (this.getPose()) {
            case STANDING:
                flag = i < 52L;
                break;
            case SITTING:
                flag = i < 40L;
                break;
            default:
                flag = false;
        }

        return flag;
    }

    private boolean isSittingDown() {
        return this.getPose() == Pose.SITTING && this.getPoseTime() < 40L;
    }

    public void sitDown() {
        if (!this.hasPose(Pose.SITTING)) {
            this.playSound(SoundEvents.CAMEL_SIT, 1.0F, 1.0F);
            this.setPose(Pose.SITTING);
            this.resetLastPoseChangeTick(this.level.getGameTime());
        }
    }

    public void standUp() {
        if (!this.hasPose(Pose.STANDING)) {
            this.playSound(SoundEvents.CAMEL_STAND, 1.0F, 1.0F);
            this.setPose(Pose.STANDING);
            this.resetLastPoseChangeTick(this.level.getGameTime());
        }
    }

    public void standUpPanic() {
        this.setPose(Pose.STANDING);
        this.resetLastPoseChangeTick(this.level.getGameTime() - 52L);
    }

    @VisibleForTesting
    public void resetLastPoseChangeTick(long p_248642_) {
        this.entityData.set(LAST_POSE_CHANGE_TICK, p_248642_);
    }

    public long getPoseTime() {
        return this.level.getGameTime() - this.entityData.get(LAST_POSE_CHANGE_TICK);
    }

    public SoundEvent getSaddleSoundEvent() {
        return SoundEvents.CAMEL_SADDLE;
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> p_252215_) {
        if (!this.firstTick && DASH.equals(p_252215_)) {
            this.dashCooldown = this.dashCooldown == 0 ? 55 : this.dashCooldown;
        }

        super.onSyncedDataUpdated(p_252215_);
    }

    protected BodyRotationControl createBodyControl() {
        return new CamelBodyRotationControl(this);
    }

    public boolean isTamed() {
        return true;
    }

    public void openCustomInventoryScreen(Player p_248613_) {
        if (!this.level.isClientSide) {
            p_248613_.openHorseInventory(this, this.inventory);
        }

    }

    class CamelBodyRotationControl extends BodyRotationControl {
        public CamelBodyRotationControl(Camel p_248635_) {
            super(p_248635_);
        }

        public void clientTick() {
            if (!Camel.this.refuseToMove()) {
                super.clientTick();
            }

        }
    }
}
