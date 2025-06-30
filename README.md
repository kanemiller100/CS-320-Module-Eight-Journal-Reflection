## CS 320 Module Eight Journal Reflection

### Submitted Artifacts
- **Contact Service Files**  
  - `Contact.java`  
  - `ContactService.java`  
  - `ContactTest.java`  
  - `ContactServiceTest.java`  
- **Summary & Reflections Report** for Project Two

---

### Reflection

**1. How can I ensure that my code, program, or software is functional and secure?**  
I write comprehensive unit tests (e.g., contact creation, update, deletion) that cover normal operation and edge cases, and enforce input validation to prevent invalid data. I also follow secure-coding best practices—such as least privilege, proper exception handling, and avoiding hard-coded credentials—to guard against common vulnerabilities (e.g., injection, null dereferences).

**2. How do I interpret user needs and incorporate them into a program?**  
I begin by clarifying functional requirements—what operations the user must perform (e.g., add, search contacts)—and nonfunctional requirements like performance and security. I translate those into service methods and test cases, then iterate based on feedback to refine behavior until it consistently meets user expectations.

**3. How do I approach designing software?**  
I start with a high-level design (UML class diagram or interface sketch), breaking the system into cohesive modules (e.g., ContactService). From there, I define clear interfaces and write tests first (TDD) to guide implementation. Finally, I refactor to improve readability and ensure each class has a single responsibility.
