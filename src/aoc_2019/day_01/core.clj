(ns aoc-2019.day-01.core)

(defn inp []
    (->> (slurp "src/aoc_2019/day_01/input.txt")
        clojure.string/split-lines
        (map #(Integer/parseInt %))))

; Fuel required to launch a given module is based on its mass. Specifically, to find the
; fuel required for a module, take its mass, divide by three, round down, and subtract 2.
(defn fuel [m]
    (- (int (/ m 3)) 2))

(defn part1 []
    (->> (inp)
        (map fuel)
        (reduce +)))

(defn rfuel2 [f]
    (if (<= f 0)
      0
      (+ f (rfuel2 (fuel f)))))

(defn fuel2 [m] (rfuel2 (fuel m)))

(defn part2 []
    (->> (inp)
        (map fuel2)
        (reduce +)))

(defn -main [& args]
    (println "Part 1:" (part1))
    (println "Part 2:" (part2)))
