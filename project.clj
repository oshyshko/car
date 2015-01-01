(defproject car "0.1.0-SNAPSHOT"
  :aot :all      ; just to mute lein warning
  :main car.Main

  :java-source-paths ["src-java"]

  :dependencies[[com.google.guava/guava "18.0"]])
