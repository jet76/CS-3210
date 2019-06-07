#lang racket
(define (genTours n)
  (add1(permutations (listMaker 2 n))))


(define (add1 n)
  (cond
   ((= 0 (length n)) '())
   ((cons (cons 1 (car n)) (add1(cdr n))))
  )
)

(define (listMaker j n)
  (cond
    ((= j n) (cons n '()))
    ((cons j(listMaker(+ j 1)n)))
  )
)

(define (score list tour)
  (cond
    ((= (length tour) 1)(pythag (car list) (find (-(car tour) 1) list)))
    ((null? (cdr list)) 0)
    ((+(pythag (find (-(car tour) 1)  list) (find (-(car (cdr tour)) 1) list))
    (score list (cdr tour))))
  )
)

(define (pythag b a)
  (cond
    ((null? a) 0)
    ((sqrt(+(sqr(- (car b) (car a))) (sqr(- (car(cdr b)) (car(cdr a)))))))
  )
)

(define (find index list)
  (cond
    ((null? list) '())
    ((> index 0) (find (- index 1) (cdr list)))
    ((= index 0) (car list))
  )
)

(define (etsp list)
  (cdr(car(sort(pair(best list (genTours (length list))) (genTours (length list))))))
)

(define (sort list)
  (cond
    ((null? list) list)
    ((insert (car list) (sort (cdr list))))
  )
)

(define (insert x list)
  (cond
    ((null? list) (cons x list))
    ((<= (car x) (car (car list))) (cons x list))
    ((cons(car list) (insert x (cdr list))))
  )
)

(define (best points tours)
  (cond
    ((= (length tours) 0) '())
    ((cons (score points (car tours))(best points (cdr tours))))
  )
)

(define (pair list1 list2)
  (cond
    ((null? list1) '())
    ((null? list2) '())   
    ((cons (cons (car list1) (cons (car list2) '())) (pair (cdr list1) (cdr list2))))
  )  
)