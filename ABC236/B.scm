(define (main args)
  (let* ((n (string->number (read-line)))
         (a (map string->number (string-split (read-line) " ")))
         (h (make-hash-table)))
    (for-each (lambda (k) (hash-table-put! h k (+ 1 (hash-table-ref/default h k 0)))) a)
    (hash-table-for-each h (lambda (k v) (cond ((= v 3) (print k))))))
  0)