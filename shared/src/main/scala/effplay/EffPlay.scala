package effplay

import org.atnos.eff._, all._, syntax.all._
import cats.data._

object EffPlay extends App {

  type StateInt[R] = Member[State[Int, ?], R]
  type WriterString[R] = Member[Writer[String, ?], R]

  type E = State[Int, ?] |: Writer[String, ?] |: NoEffect

  def putAndTell[R : StateInt : WriterString](i: Int): Eff[R, Int] =
    for {
      _ <- put(i)
      _ <- tell("stored " + i)
    } yield i

  putAndTell[E](7).runState(0).runWriter.run

}
