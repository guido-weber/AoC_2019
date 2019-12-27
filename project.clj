(defproject aoc-2019 "0.1.0-SNAPSHOT"
  :description "Solutions for Advent of Code 2019"
  :license {:name "Unlicense"
            :url "https://unlicense.org"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :main ^:skip-aot aoc-2019.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
