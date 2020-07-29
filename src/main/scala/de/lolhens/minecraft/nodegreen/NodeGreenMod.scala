package de.lolhens.minecraft.nodegreen

import de.lolhens.minecraft.nodegreen.block.NodeBlock
import de.lolhens.minecraft.nodegreen.node.Inject
import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.metadata.ModMetadata
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.{BlockItem, Item, ItemGroup}
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

import scala.jdk.CollectionConverters._

object NodeGreenMod extends ModInitializer {
  val metadata: ModMetadata = {
    FabricLoader.getInstance().getEntrypointContainers("main", classOf[ModInitializer])
      .iterator().asScala.find(this eq _.getEntrypoint).get.getProvider.getMetadata
  }

  val injectNodeId: Identifier = Inject.identifier
  val injectNodeBlock: NodeBlock = Inject.createBlock
  val injectNodeBlockEntity: BlockEntityType[_] = injectNodeBlock.blockEntityType

  override def onInitialize(): Unit = {
    Registry.register(Registry.BLOCK, injectNodeId, injectNodeBlock)
    Registry.register(Registry.ITEM, injectNodeId, new BlockItem(injectNodeBlock, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)))
    Registry.register(Registry.BLOCK_ENTITY_TYPE, injectNodeId, injectNodeBlockEntity)
  }
}
