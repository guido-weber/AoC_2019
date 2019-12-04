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

(println "Part 1:" (part1))
