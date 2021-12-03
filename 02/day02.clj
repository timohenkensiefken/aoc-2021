(defn steer [] 
  (with-open [rdr (clojure.java.io/reader "input")] 
    (reduce 
      (fn [sums item] (let [[x y aim] sums 
                            [direction steps] (update (clojure.string/split item #" ") 1 #(Integer/parseInt %))]
        (case direction
          "forward" [(+ x steps) (+ y (* aim steps)) aim]
          "down" [x y (+ aim steps)]
          "up" [x y (- aim steps)])                                   
      )) 
      [0, 0, 0] (line-seq rdr)
    )
  )
)

(defn steer-mult [] (let [[a b _] (steer)] (* a b)))
