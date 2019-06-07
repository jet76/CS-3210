#lang racket
(define (store value var table)
   (if (contains? var table)
       (change value var table)
       (cons (cons var (cons value '())) table)
   )
)

(define (change value var table)
   (if (eq? (car (car table)) var)
       (cons (cons (car (car table)) (cons value '())) (cdr table))
       (cons (car table) (change value var (cdr table)))
   )
)

(define (retrieve var table)
   (if (eq? (car (car table)) var)
       (car (cdr (car table)))
       (retrieve var (cdr table))
   )
)

(define (contains? var table)
  (if (null? table)
      #f
      (if (eq? (car (car table)) var)
         #t
         (contains? var (cdr table))
      )
   )
)

(define (assign x y table)
   (store (retrieve y table) x table)
)