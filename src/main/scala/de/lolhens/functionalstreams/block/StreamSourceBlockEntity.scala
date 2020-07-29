package de.lolhens.functionalstreams.block

import net.minecraft.block.Blocks
import net.minecraft.block.entity.{BlockEntity, BlockEntityType}
import net.minecraft.util.Tickable

class StreamSourceBlockEntity(`type`: BlockEntityType[StreamSourceBlockEntity]) extends BlockEntity(`type`) with Tickable {
  override def tick(): Unit = {
    world.setBlockState(pos.up(), Blocks.SLIME_BLOCK.getDefaultState)
  }
}
