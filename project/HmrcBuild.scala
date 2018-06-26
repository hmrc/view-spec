import sbt.Keys._
import sbt._
import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.versioning.SbtGitVersioning


object HmrcBuild extends Build {

  import BuildDependencies._
  import uk.gov.hmrc.DefaultBuildSettings._

  val appName = "view-spec"

  lazy val ViewSpec = Project(appName, file("."))
    .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning)
    .settings(
      targetJvm := "jvm-1.8",
      libraryDependencies ++= Test.test,
      Developers()
    )
}

private object BuildDependencies {

  object Compile {
  }

  sealed abstract class Test(scope: String) {
    lazy val test = Seq(
      "org.scalatest" %% "scalatest" % "2.2.6" % scope,
      "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % scope,
      "uk.gov.hmrc" %% "hmrctest" % "2.3.0" % scope,
      "org.pegdown" % "pegdown" % "1.6.0" % scope,
      "org.jsoup" % "jsoup" % "1.8.3" % scope,
      "org.mockito" % "mockito-all" % "1.9.5" % scope,
      "org.mockito" % "mockito-core" % "1.9.0" % scope
    )
  }

  object Test extends Test("test")

}

object Developers {

  def apply() = developers := List[Developer]()
}
