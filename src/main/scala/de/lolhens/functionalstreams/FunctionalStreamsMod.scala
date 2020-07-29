package de.lolhens.functionalstreams

import de.lolhens.functionalstreams.block.StreamSourceBlock
import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.metadata.ModMetadata
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.{BlockItem, Item, ItemGroup}
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

import scala.jdk.CollectionConverters._

object FunctionalStreamsMod extends ModInitializer {
  val metadata: ModMetadata = {
    FabricLoader.getInstance().getEntrypointContainers("main", classOf[ModInitializer])
      .iterator().asScala.find(this eq _.getEntrypoint).get.getProvider.getMetadata
  }

  val streamSourceBlockId = new Identifier(metadata.getId, "stream_source")
  val streamSourceBlock: StreamSourceBlock = new StreamSourceBlock()
  val streamSourceBlockEntity: BlockEntityType[_] = streamSourceBlock.blockEntityType

  override def onInitialize(): Unit = {
    Registry.register(Registry.BLOCK, streamSourceBlockId, streamSourceBlock)
    Registry.register(Registry.ITEM, streamSourceBlockId, new BlockItem(streamSourceBlock, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)))
    Registry.register(Registry.BLOCK_ENTITY_TYPE, streamSourceBlockId, streamSourceBlockEntity)
  }
}
