package de.lolhens.minecraft.nodegreen.node

import de.lolhens.minecraft.nodegreen.NodeGreenMod
import de.lolhens.minecraft.nodegreen.block.{NodeBlock, NodeBlockEntity}
import de.lolhens.minecraft.nodegreen.node.Node.{Input, NodeFactory, Output}
import net.minecraft.util.Identifier
import net.minecraft.util.math.{BlockPos, Direction}
import net.minecraft.world.World

trait Node {
  type Self <: Node

  def factory: NodeFactory[Self]

  def label: String = factory.name

  def inputs: List[Node.Input[_]]

  def outputs: List[Node.Output[_]]

  protected def world: World

  protected def pos: BlockPos

  protected def sendMessage[A](output: Output[A], value: A): Unit = {
    val offset = pos.offset(output.direction)
    world.getBlockState(offset).getBlock match {
      case nodeBlock: NodeBlock =>
        world.getBlockEntity(offset) match {
          case nodeBlockEntity: NodeBlockEntity =>
            nodeBlockEntity.node.receiveMessage(null, value) // TODO: add type info to Port
        }
    }
  }

  def receiveMessage[A](input: Input[A], value: A): Unit
}

object Node {

  trait NodeFactory[+A <: Node] {
    def name: String

    def color: Node.Color

    def identifier: Identifier = new Identifier(
      NodeGreenMod.metadata.getId,
      name.toLowerCase.replaceAll(" ", "_")
    )

    def createNode(world: World, pos: BlockPos): A

    def createBlock: NodeBlock
  }

  trait DefaultNodeFactory[+A <: Node] extends NodeFactory[A] {
    override def createBlock: NodeBlock = new NodeBlock(this)
  }

  sealed trait Color

  object Color {

    case object Red extends Color

    case object Green extends Color

    case object Blue extends Color

  }

  sealed trait Port[A] {
    def index: Int

    def name: String
  }

  case class Input[A](index: Int, name: String) extends Port[A]

  case class Output[A](index: Int, name: String, direction: Direction) extends Port[A]

}
