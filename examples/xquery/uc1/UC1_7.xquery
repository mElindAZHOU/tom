<!-- List the titles and years of all books published by Addison-Wesley after 1991, in alphabetic order.

Solution in XQuery: -->

<bib>
  {
    for $b in doc("http://bstore1.example.com/bib.xml")//book
    where $b/publisher = "Addison-Wesley" and $b/@year > 1991
    order by $b/title
    return
        <book>
            { $b/@year }
            { $b/title }
        </book>
  }
</bib> 

