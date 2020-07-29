package de.lolhens.minecraft.nodegreen.block

import de.lolhens.minecraft.nodegreen.node.Node
import de.lolhens.minecraft.nodegreen.node.Node.NodeFactory
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block._
import net.minecraft.block.entity.{BlockEntity, BlockEntityType}
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.world.BlockView

class NodeBlock(val nodeFactory: NodeFactory[Node]) extends Block(NodeBlock.settings) with BlockEntityProvider {
  val blockEntityType: BlockEntityType[NodeBlockEntity] =
    BlockEntityType.Builder.create(() => new NodeBlockEntity(blockEntityType, this), this)
      .build(null)

  override def createBlockEntity(world: BlockView): BlockEntity = blockEntityType.instantiate()

  override def getPistonBehavior(state: BlockState): PistonBehavior = PistonBehavior.BLOCK
}

object NodeBlock {
  private val settings =
    FabricBlockSettings
      .of(Material.STONE)
      .requiresTool()
      .hardness(3.0F)
}

