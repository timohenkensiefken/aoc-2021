(def closing_map {\( \) 
                  \[ \] 
                  \{ \} 
                  \< \>})

(defn walk [string] (reduce (fn [stack c] 
                              (cond
                                (number? stack) stack
                                (contains? closing_map c) (conj stack c)
                                (contains? (set (vals closing_map)) c) 
                                  (if 
                                    (= c (closing_map (peek stack))) 
                                    (pop stack) 
                                    (case c
                                      \) 3
                                      \] 57
                                      \} 1197
                                      \> 25137))))
                            '() 
                            string))

(defn score [leftover] (reduce 
                         (fn [sum c] (+ (* sum 5) (case c
                                                    \( 1
                                                    \[ 2
                                                    \{ 3
                                                    \< 4)))
                         0
                         leftover))

(defn syntax_check [] 
  (with-open [rdr (clojure.java.io/reader "input")] 
    (->> rdr
         line-seq
         (map walk)
         (filter #(not (number? %)))
         (map score)
         sort
         (#(nth % (int (/ (count %) 2))))
         println)
  )
)
