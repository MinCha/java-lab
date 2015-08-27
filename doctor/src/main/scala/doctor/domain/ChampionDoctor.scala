package doctor.domain

import java.lang.Math._

import dto.Stats.ChampionStats

case class ChampionDoctor(n: String, b: List[ChampionStat], w: List[ChampionStat] = List.empty)

case class ChampionStat(stat: ChampionStats) {
  val TripleKillWeight = 1.2D
  val QuadraKillWeight = 1.3D
  val PentaKillWeight = 1.4D

  val s = stat.getStats

  def name = champion.get.name

  def champion = Champion.findBy(stat.getId)

  def gameCount = s.getTotalSessionsPlayed

  def winRate = ((s.getTotalSessionsWon / gameCount.toDouble) * 100).toInt

  def multipleKill = {
    (s.getTotalDoubleKills +
      (s.getTotalTripleKills * TripleKillWeight) +
      (s.getTotalQuadraKills * QuadraKillWeight) +
      (s.getTotalPentaKills * PentaKillWeight)
      ).toInt
  }

  def turret = s.getTotalTurretsKilled

  def kda = ((s.getTotalChampionKills + 1).toDouble + (s.getTotalAssists + 1)) / (s.getTotalDeathsPerSession + 1)

  def effect = s.getTotalDamageDealt + s.getTotalDamageTaken + s.getTotalHeal

  def score =
    ((winRate + 1) * log(gameCount)) +
      log(multipleKill + turret + 1) +
      ((kda + 1) * 10) +
      log(effect)


  override def toString: String = {
    "Name->%s, WinRate->%s, Turret->%s, KDA->%s, Effect->%s, MultipleKill->%s, GameCount->%s, Score->%s".format(name, winRate, turret, kda, effect, multipleKill, gameCount, score)
  }
}
