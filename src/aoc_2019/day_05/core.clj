(ns aoc-2019.day-05.core)

(defn inp []
    (vec
        (map
            #(Integer/parseInt %)
            (mapcat
                #(clojure.string/split % #",")
                (clojure.string/split-lines
                    (slurp "src/aoc_2019/day_05/input.txt"))))))

(defn decode [instruction]
    (let [[oc_mode adr1 adr2 adr3] instruction
          [mode3 mode2 mode1 oc2 oc1] (map #(Integer/parseInt (str %)) (format "%05d" oc_mode))
          opcode (+ (* oc2 10) oc1)]
        [opcode mode1 mode2 mode3 adr1 adr2 adr3]))

(defn get_arg [prog mode adr]
    (if (= mode 0) (get prog adr) adr))

(defn get_input []
    (print "> ")
    (flush)
    (Integer/parseInt (read-line)))

(defn prinstr [ip opcode mode1 mode2 mode3 adr1 adr2 adr3]
    (println
        (if (< opcode 3)
            (format "%3d: %2d %5d(%d) %5d(%d) %5d(%d)" ip opcode adr1 mode1 adr2 mode2 adr3 mode3)
            (format "%3d: %2d %5d(%d)" ip opcode adr1 mode1))))

(defn step [prog ip]
    (let [[opcode mode1 mode2 mode3 adr1 adr2 adr3] (decode (subvec prog ip))]
        (prinstr ip opcode mode1 mode2 mode3 adr1 adr2 adr3)
        (case opcode
            1  [(assoc prog adr3 (+ (get_arg prog mode1 adr1) (get_arg prog mode2 adr2))), (+ ip 4)]
            2  [(assoc prog adr3 (- (get_arg prog mode1 adr1) (get_arg prog mode2 adr2))), (+ ip 4)]
            3  [(assoc prog adr1 (get_input)), (+ ip 2)]
            4  [(do (println (get_arg prog mode1 adr1)) prog), (+ ip 2)]
            99 [prog, nil])))

(defn run [prog ip]
    (let [[newprog newpos] (step prog ip)]
      (if (= newpos nil)
        newprog
        (do
            (println (subvec newprog 220 236))
            (run newprog newpos)))))

(defn -main [& args]
    (println "Part 1:")
    (run (inp) 0))
