package effplay

import org.atnos.eff._, all._, syntax.all._
import cats.data._

object EffPlay extends App {

  type RNG[R] = Member[State[Int, ?], R]
  type Log[R] = Member[Writer[String, ?], R]
  type Env[R] = Member[Reader[String, ?], R]

  type S = State[Int, ?] |: Writer[String, ?]  |: Reader[String, ?]|: NoEffect

  def putAndTell[R : RNG : Log: Env](i: Int) =
    for {
      _ <- put(i)
      _ <- tell("stored " + i)
    } yield i

  putAndTell[S](4).runState(0).runWriter.runReader("").run

}
object EffPlay2 extends App {

  type Env[E] = Member[Reader[String, ?], E]
  type Log[E] = Member[Writer[String, ?], E]
  type RNG[E] = Member[State[Int, ?], E]

  type S = State[Int, ?] |:  Writer[String, ?] |: Reader[String, ?] |: NoEffect

  def putAndTell[E: Env: Log: RNG](i: Int): Eff[E, Int] = for {
    _ <- put(i)
    _ <- tell("stored " + i)
  } yield (i)

  putAndTell[S](4).runReader("").runState(0).runWriter.run
}
object EffPlayBroke extends App {

  type Env[E] = Member[Reader[String, ?], E]
  type Log[E] = Member[Writer[String, ?], E]
  type RNG[E] = Member[State[Int, ?], E]

  type S = State[Int, ?] |:  Writer[String, ?] |: Reader[String, ?] |: NoEffect

  def putAndTell[E: Env: Log: RNG](i: Int): Eff[E, Int] = for {
    _ <- put(i)
    _ <- tell("stored " + i)
  } yield (i)

  putAndTell[S](4).runReader("").runWriter.runState(0).run
}