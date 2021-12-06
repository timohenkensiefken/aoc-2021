(defn topovec [fish] 
  (reduce (fn [popul age] (update popul age #(if (nil? %) 1 (+ % 1)))) [0 0 0 0 0 0 0 0 0] fish)
  )

(defn sim [a] 
  [(nth a 1) (nth a 2) (nth a 3) (nth a 4) (nth a 5) (nth a 6) (+ (nth a 0) (nth a 7)) (nth a 8) (nth a 0)]
  )

(defn lanternfish [days] 
  (with-open [rdr (clojure.java.io/reader "input")] 
    (println (apply + (nth (iterate sim 
      (topovec (map #(Integer/parseInt %) (clojure.string/split (first (line-seq rdr)) #",")))) days)))
  )
)

