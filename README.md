# Assignment 3 — Bridge Pattern

**Student:** Ulzhan Musakhan  
**Group:** SE-2524  
**Topic:** A — Drawing  
**Repository:** https://github.com/httpskwiksx/bridge-pattern  
**Base Commit:** `4c106fd7c07b6c2eb8fac8c1788288c678e3b15b`

---

## Project Description

This project demonstrates the **Bridge Design Pattern** in Java.

The application separates two independently varying dimensions:

- **Shapes:** `Circle`, `Square`
- **Rendering implementations:** `VectorRenderer`, `RasterRenderer`, `AsciiRenderer`

The Bridge pattern connects these two hierarchies using composition, allowing shapes and rendering implementations to vary independently.

The project uses Java 17 and contains an automated demonstration with checks T1–T7.

---

## Bridge Pattern Role Map

| Bridge Role | Class | Source File |
|---|---|---|
| Abstraction | `Shape` | `src/Shape.java` |
| Refined Abstraction A1 | `Circle` | `src/Circle.java` |
| Refined Abstraction A2 | `Square` | `src/Square.java` |
| Implementor | `Renderer` | `src/Renderer.java` |
| Implementation I1 | `VectorRenderer` | `src/VectorRenderer.java` |
| Implementation I2 | `RasterRenderer` | `src/RasterRenderer.java` |
| Implementation I3 | `AsciiRenderer` | `src/AsciiRenderer.java` |
| Client | `Main` | `src/Main.java` |

---

## Bridge Connection

The bridge is the `Renderer` interface reference stored inside the `Shape` abstraction:

```java
protected Renderer renderer;
```

The implementation is supplied through the `Shape` constructor.

Because `Shape` depends only on the `Renderer` interface, the abstraction does not depend on concrete implementations such as `VectorRenderer`, `RasterRenderer`, or `AsciiRenderer`.

This allows both hierarchies to be extended independently.

---

## Main Operations

### `execute()`

Each refined abstraction implements the `execute()` method and delegates the actual rendering work through the stored `Renderer` reference.

Example from `Circle`:

```java
@Override
public String execute() {
    return renderer.renderCircle(radius);
}
```

Example from `Square`:

```java
@Override
public String execute() {
    return renderer.renderSquare(side);
}
```

This is delegation because the shape does not perform the rendering itself. It asks the selected `Renderer` implementation to perform it.

### `setImplementation(...)`

The rendering implementation can be replaced at runtime:

```java
public void setImplementation(Renderer renderer) {
    this.renderer = renderer;
}
```

This method is used in T5 to demonstrate runtime implementation switching on the same abstraction object.

---

## Domain Data

The required fixed sample data is:

- `Circle` radius = `2`
- `Square` side = `3`

Each abstraction also has its own ID.

The shape-specific state remains unchanged when the rendering implementation is replaced.

---

## Runtime Switching — T5

T5 demonstrates that the same `Circle` object first uses `VectorRenderer` and then switches to `RasterRenderer`.

The test verifies:

- the object reference remains the same using `==`;
- the ID remains unchanged;
- the radius remains unchanged;
- the rendering result changes after replacing the implementation.

Expected evidence:

```text
T5 PASS | sameObject=true | stateUnchanged=true
 before=VECTOR circle radius=2.0
 after=RASTER circle radius=2.0
```

The abstraction object is not recreated. Only its `Renderer` reference is changed.

---

## Independent Extension

The initial version of the project contained:

- `VectorRenderer`
- `RasterRenderer`

After the base version was completed and committed, a new implementation was added:

- `AsciiRenderer`

The base commit is:

```text
4c106fd7c07b6c2eb8fac8c1788288c678e3b15b
```

The new `AsciiRenderer` was added without modifying:

- `Shape`
- `Circle`
- `Square`
- `Renderer`
- `VectorRenderer`
- `RasterRenderer`

Only the new `AsciiRenderer.java` file and `Main.java` were changed during the extension step.

This demonstrates that a new implementation can be added independently without changing the existing abstraction hierarchy.

The source changes are recorded in:

```text
extension.diff
```

---

## Demonstration Checks

The program runs seven automated checks.

| Test | Combination / Action |
|---|---|
| T1 | `Circle` + `VectorRenderer` |
| T2 | `Circle` + `RasterRenderer` |
| T3 | `Square` + `VectorRenderer` |
| T4 | `Square` + `RasterRenderer` |
| T5 | Same `Circle` switches from `VectorRenderer` to `RasterRenderer` |
| T6 | `Circle` + `AsciiRenderer` |
| T7 | `Square` + `AsciiRenderer` |

Each check compares the actual result with the expected result and calculates `PASS` or `FAIL`.

---

## Expected Results

```text
T1 PASS | Circle + VectorRenderer | result=VECTOR circle radius=2.0
T2 PASS | Circle + RasterRenderer | result=RASTER circle radius=2.0
T3 PASS | Square + VectorRenderer | result=VECTOR square side=3.0
T4 PASS | Square + RasterRenderer | result=RASTER square side=3.0
T5 PASS | sameObject=true | stateUnchanged=true
 before=VECTOR circle radius=2.0
 after=RASTER circle radius=2.0
T6 PASS | Circle + AsciiRenderer | result=ASCII circle radius=2.0
T7 PASS | Square + AsciiRenderer | result=ASCII square side=3.0

SUMMARY: 7/7 PASS
```

The captured output is also stored in `demo-output.txt`.

---

## Build and Run

### Requirements

- Java JDK 17
- No external dependencies

Compile the project from the project root:

```bash
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
```

Run the automated demonstration:

```bash
java -cp out Main --demo
```

Expected final result:

```text
SUMMARY: 7/7 PASS
```

---

## Project Structure

```text
bridge-pattern/
│
├── src/
│   ├── Main.java
│   ├── Shape.java
│   ├── Circle.java
│   ├── Square.java
│   ├── Renderer.java
│   ├── VectorRenderer.java
│   ├── RasterRenderer.java
│   └── AsciiRenderer.java
│
├── sources.txt
├── demo-output.txt
├── extension.diff
├── README.md
└── report.pdf
```

---

## Why Bridge Is Appropriate

Without Bridge, separate classes could be required for every combination of shape and renderer, for example:

```text
VectorCircle
RasterCircle
AsciiCircle
VectorSquare
RasterSquare
AsciiSquare
```

Bridge avoids this class-explosion problem by separating the shape hierarchy from the rendering hierarchy.

A `Circle` or `Square` can work with any object that implements the `Renderer` interface.

---

## Bridge vs Adapter

The **Bridge** pattern is designed to separate abstraction from implementation so that both can evolve independently.

The **Adapter** pattern is used to make already existing incompatible interfaces work together.

In this project, `Shape` and `Renderer` are designed as two independent hierarchies from the beginning, so Bridge is the appropriate pattern.

---

## Conclusion

The project demonstrates the main properties of the Bridge pattern:

- separation of abstraction and implementation;
- composition instead of creating combination subclasses;
- delegation through an interface;
- runtime implementation replacement;
- preservation of abstraction state;
- independent addition of a new implementation;
- support for the Open/Closed Principle.