(def valid_symbols {#{\a \b \c    \e \f \g} 0 
                    #{      \c       \f   } 1 
                    #{\a    \c \d \e    \g} 2 
                    #{\a    \c \d    \f \g} 3 
                    #{   \b \c \d    \f   } 4 
                    #{\a \b    \d    \f \g} 5 
                    #{\a \b    \d \e \f \g} 6 
                    #{\a    \c       \f   } 7 
                    #{\a \b \c \d \e \f \g} 8 
                    #{\a \b \c \d    \f \g} 9 })

(def gen_perm (let [s0 #{\a \b \c \d \e \f \g}] (for [a s0 :let [s1 (disj s0 a)] 
                                         b s1 :let [s2 (disj s1 b)] 
                                         c s2 :let [s3 (disj s2 c)] 
                                         d s3 :let [s4 (disj s3 d)] 
                                         e s4 :let [s5 (disj s4 e)] 
                                         f s5 :let [s6 (disj s5 f)] 
                                         g s6] {\a a \b b \c c \d d \e e \f f \g g})))

(defn permutate [perm a] (map #(perm %) a))

(defn get_perm [p10] 
  (reduce (fn [perms input] (filter #(contains? valid_symbols (set (permutate % input))) perms)) gen_perm p10))

(defn segment [] 
  (with-open [rdr (clojure.java.io/reader "input")] 
    (->> rdr
         line-seq
         (mapv (fn [line] (map #(clojure.string/split % #" ") (clojure.string/split line #" \| "))))
         (map (fn [dec_line] 
            (let [[p10 pO] dec_line] 
              (Integer/parseInt (reduce str "" (mapv 
                                                 #(valid_symbols (set (permutate (first (get_perm p10)) %))) 
                                                 pO))))))
         (reduce + 0)
         println)
  )
)
