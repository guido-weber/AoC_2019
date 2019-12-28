(ns aoc-2019.day-04)

(defn criteria? [n]
    (let [[a,b,c,d,e,f] (map (fn [x] (int x)) (str n))]
      (and
          (<= a b)
          (<= b c)
          (<= c d)
          (<= d e)
          (<= e f)
          (or
              (= a b)
              (= b c)
              (= c d)
              (= d e)
              (= e f)))))

(defn part1 []
    (->> (range 138307 654505)
        (filter criteria?)
        (count)))

(defn criteria2? [n]
    (let [[a,b,c,d,e,f] (map (fn [x] (int x)) (str n))]
      (and
          (<= a b)
          (<= b c)
          (<= c d)
          (<= d e)
          (<= e f)
          (>= (count (filter #(= (second %) 2) (frequencies (str n)))) 1))))

(defn part2 []
    (->> (range 138307 654505)
        (filter criteria2?)
        (count)))

(defn -main [& args]
    (println "Part 1:" (part1))
    (println "Part 2:" (part2)))
