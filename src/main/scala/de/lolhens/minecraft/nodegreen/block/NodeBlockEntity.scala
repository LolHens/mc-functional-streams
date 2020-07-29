package de.lolhens.minecraft.nodegreen.block

import de.lolhens.minecraft.nodegreen.node.Node
import net.minecraft.block.Blocks
import net.minecraft.block.entity.{BlockEntity, BlockEntityType}
import net.minecraft.util.Tickable

class NodeBlockEntity(`type`: BlockEntityType[NodeBlockEntity], block: NodeBlock) extends BlockEntity(`type`) with Tickable {
  val node: Node = block.nodeFactory.createNode(world, pos)

  override def tick(): Unit = {
    world.setBlockState(pos.up(), Blocks.SLIME_BLOCK.getDefaultState)
  }
}
