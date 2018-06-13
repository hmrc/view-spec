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
      libraryDependencies ++= Seq(
        Test.scalaTest,
        Test.pegdown,
	Test.playSpec
      ),
      Developers()
    )
}

private object BuildDependencies {

  object Compile {
  }

  sealed abstract class Test(scope: String) {
    val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % scope
    val pegdown = "org.pegdown" % "pegdown" % "1.5.0" % scope
    val playSpec = "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0" % "test"
  }

  object Test extends Test("test")

}

object Developers {

  def apply() = developers := List[Developer]()
}
