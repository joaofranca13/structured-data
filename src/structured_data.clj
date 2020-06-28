(ns structured-data)

(defn do-a-thing [x]
  (let [doisx (+ x x)]
    (Math/pow doisx doisx )))

(defn spiff [v]
  (let [primeiro (get v 0)
        terceiro (get v 2)]
    (+ primeiro terceiro)))

(defn cutify [v]
  (conj v "<3"))

(defn spiff-destructuring [v]
  (let [[x y z] v ]
    (+ x z)))

(defn point [x y]
  [x y])

(defn rectangle [bottom-left top-right]
  [bottom-left top-right])

(defn width [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    do (- x2 x1)))

(defn height [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    do (- y2 y1)))
  
(defn square? [rectangle]
  (== (width rectangle) (height rectangle)))

(defn area [rectangle]
  (* (width rectangle) (height rectangle)))

(defn contains-point? [rectangle point]
  (let [[[x1 y1] [x2 y2]] rectangle
        [x3 y3] point]
    (if (and (>= x2 x3 x1) (>= y2 y3 y1)) true false)))

(defn contains-rectangle? [outer inner]
  (let [[[x1 y1] [x2 y2]] outer 
         [[x3 y3] [x4 y4]] inner]
    (if (and (>= x3 x1) (>= y3 y1) (>= x2 x4) (>= y2 y4)) true false)))

(defn title-length [book]
  (count (:title book)))

(defn author-count [book]
  (count (get book :authors)))

(defn multiple-authors? [book]
  (if (> (author-count book) 1) true false))

(defn add-author [book new-author]
  (let [author (get book :authors) 
        new-authors(conj author new-author)]
    (assoc book :authors new-authors)))

(defn alive? [author]
  (not (contains? author :death-year)))

(defn element-lengths [collection]
  (let [contar (fn [x] (count x))]
    (map contar collection)))

(defn second-elements [collection]
  (let [segundo-item (fn [x] (get x 1))]
    (map segundo-item collection)))

(defn titles [books]
  (map :title books))

(defn monotonic? [a-seq]
  (if (or (apply <= a-seq) (apply >= a-seq)) true false))

(defn stars [n]
  (apply str (repeat n "*")))


(defn toggle [a-set elem]
  (if (contains? a-set elem) (disj a-set elem) (conj a-set elem)))

(defn contains-duplicates? [a-seq]
  (if (> (count a-seq) (count (set a-seq))) true false))

(defn old-book->new-book [book]
  (assoc book :authors (set (:authors book))))

(defn has-author? [book author]
  (if (contains? (get book :authors) author) true false))

(defn authors [books]
  (apply clojure.set/union (map :authors books)))

(defn all-author-names [books]
  (set (map :name (authors books))))

(defn author->string [author]
  (let [author-name
        (:name author)
        birth-date
        (:birth-year author)
        death-date
        (:death-year author)]
    (cond
      (:death-year author) (str author-name " (" birth-date " - " death-date ")")
      (:birth-year author) (str author-name " (" birth-date " - )")
      :else author-name)))

(defn authors->string [authors]
  (apply str (interpose ", " (map author->string authors))))

(defn book->string [book]
  (let [titulo
        (:title book)
        autor
        (authors->string (:authors book))]
    (apply str (interpose ", written by " [titulo autor]))))

(defn books->string [books]
  (cond
    (= (count books) 1) (apply str (count books)" book. "(apply str (map book->string books)) ".")
    (> (count books) 1) (apply str (count books)" books. "(apply str (interpose " | "(map book->string books)))".")
    :else "No books."))

(defn books-by-author [author books]
  (filter (fn [book] (has-author? book author)) books))

(defn author-by-name [name authors]
  (first (filter (fn [author] (= name (:name author))) authors)))

(defn living-authors [authors]
  (filter alive? authors))

(defn has-a-living-author? [book]
  (not (empty? (filter alive? (:authors book)))))

(defn books-by-living-authors [books]
  (filter (fn [book] (has-a-living-author? book)) books))

; %________%
