(ns aoc-2019.day-02.core)

(defn inp []
    (vec
        (map
            (fn [x] (Integer/parseInt x))
            (mapcat
                (fn [l] (clojure.string/split l #","))
                (clojure.string/split-lines
                    (slurp "src/aoc_2019/day_02/input.txt"))))))

(defn do_op [opcode arg1 arg2]
    (case opcode
        1 (+ arg1 arg2)
        2 (* arg1 arg2)))

(defn step [prog ip]
    (let [op (subvec prog ip)
          [opcode adr1 adr2 adr3] op]
      (if (= opcode 99)
        [prog nil]
        [(assoc prog adr3 (do_op opcode (get prog adr1) (get prog adr2))) (+ ip 4)])))

; (println (step [1,9,10,3,2,3,11,0,99,30,40,50] 0))

(defn run [prog ip]
    (let [[newprog newpos] (step prog ip)]
      (if (= newpos nil)
        newprog
        (run newprog newpos))))

; (println (run [1,9,10,3,2,3,11,0,99,30,40,50] 0))

(defn run_prog [noun verb]
    (-> (inp)
        (assoc 1 noun)
        (assoc 2 verb)
        (run 0)
        (get 0)))
;    (get (run (assoc (assoc (inp) 1 noun) 2 verb) 0) 0))

(defn part2 []
    (loop [noun 0 verb 0]
        (let [result (run_prog noun verb)]
            (cond
                (= result 19690720) (+ (* noun 100) verb)
                (< verb 99) (recur noun (inc verb))
                (< noun 99) (recur (inc noun) 0)))))

(defn -main [& args]
    (println "Part 1:" (run_prog 12 2))
    (println "Part 2:" (part2)))
