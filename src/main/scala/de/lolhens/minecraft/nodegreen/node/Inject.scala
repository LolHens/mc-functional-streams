package de.lolhens.minecraft.nodegreen.node

import de.lolhens.minecraft.nodegreen.block.{NodeBlock, NodeBlockEntity}
import de.lolhens.minecraft.nodegreen.node.Node.NodeFactory
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.{BlockPos, Direction}
import net.minecraft.util.{ActionResult, Hand}
import net.minecraft.world.World

class Inject(protected val world: World,
             protected val pos: BlockPos) extends Node {
  override type Self = Inject

  override def factory: NodeFactory[Inject] = Inject

  override def inputs: List[Node.Input[_]] = List()

  override def outputs: List[Node.Output[_]] = List(Inject.Output)

  override def receiveMessage[A](input: Node.Input[A], value: A): Unit = ()
}

object Inject extends NodeFactory[Inject] {
  val Output: Node.Output[String] = Node.Output(0, "output", Direction.DOWN)

  override def name: String = "Inject"

  override def color: Node.Color = Node.Color.Blue

  override def createNode(world: World, pos: BlockPos): Inject =
    new Inject(world, pos)

  override def createBlock: NodeBlock = new NodeBlock(this) {
    override def onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hand: Hand, hit: BlockHitResult): ActionResult = {
      val message: String = "Hello World"
      world.getBlockEntity(pos).asInstanceOf[NodeBlockEntity].node.sendMessage(Output, message)
      ActionResult.SUCCESS
    }
  }
}
