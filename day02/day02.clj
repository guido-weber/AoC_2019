(defn pi [x] (Integer/parseInt x))

(defn inp []
    (map pi (clojure.string/split (first (clojure.string/split-lines (slurp "input.txt"))) #",")))

(println (inp))
