(define (main args) 
  (let* ((first (map string->number (string-split (read-line) " ")))
         (n (car first))
         (m (cadr first))
         (s (string-split (read-line) " "))
         (t (string-split (read-line) " "))
         (h (make-hash-table 'string=?)))
    (for-each (lambda (k) (hash-table-put! h k #t)) t)
    (for-each (lambda (k) (print (if (hash-table-exists? h k) "Yes" "No"))) s))
    0)