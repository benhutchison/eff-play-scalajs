package effplay

import org.atnos.eff._, all._, syntax.all._
import cats.data._

object EffPlayXorWorking extends App {

  type Env[E] = Member[Reader[String, ?], E]
  type Xors[E] = Member[Xor[String, ?], E]
  type RNG[E] = Member[State[Int, ?], E]

  type S = State[Int, ?] |: Xor[String, ?] |: Reader[String, ?] |: NoEffect

  def putAndTell[E: Env: Xors: RNG](i: Int): Eff[E, Int] = for {
    _ <- put(i)
  } yield (i)

  putAndTell[S](4).runState(0).runReader("").runXor.run
}
object EffPlayXorBroke extends App {

  type Env[E] = Member[Reader[String, ?], E]
  type Xors[E] = Member[Xor[String, ?], E]
  type RNG[E] = Member[State[Int, ?], E]

  type S = State[Int, ?] |: Xor[String, ?] |: Reader[String, ?] |: NoEffect

  def putAndTell[E: Env: Xors: RNG](i: Int): Eff[E, Int] = for {
    _ <- put(i)
  } yield (i)

  putAndTell[S](4).runReader("").runXor.runState(0).run
}