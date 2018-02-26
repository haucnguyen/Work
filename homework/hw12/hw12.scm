(define (find s predicate)
  (cond
    ((null? s) 'False)
    ((predicate (car s)) (car s))
  (else (find (cdr-stream s) predicate)))
  )

(define (scale-stream s k)
  (cond
    ((null? s) nil)
    (else (cons-stream (* (car s) k) (scale-stream (cdr-stream s) k))))
    )

(define (has-cycle s)
  (define seen s)

  (define (check s)
    (cond
    ((null? s) 'False)
    ((eq? (cdr-stream s) seen) 'True)
    (else (check (cdr-stream s))))
    )
(check s)
    )
(define (has-cycle-constant s)
  'YOUR-CODE-HERE
)
