package de.lolhens.minecraft.nodegreen.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block._
import net.minecraft.block.entity.{BlockEntity, BlockEntityType}
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.world.BlockView

class StreamSourceBlock() extends Block(StreamSourceBlock.settings) with BlockEntityProvider {
  val blockEntityType: BlockEntityType[StreamSourceBlockEntity] =
    BlockEntityType.Builder.create(() => new StreamSourceBlockEntity(blockEntityType), this)
      .build(null)

  override def createBlockEntity(world: BlockView): BlockEntity = blockEntityType.instantiate()

  override def getPistonBehavior(state: BlockState): PistonBehavior = PistonBehavior.BLOCK
}

object StreamSourceBlock {
  private val settings =
    FabricBlockSettings
      .of(Material.STONE)
      .requiresTool()
      .hardness(3.0F)
}

