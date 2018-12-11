import java.util.Scanner
import java.util.Random

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val random = Random()
    val board = Board()
    var inputCell = -1
    var computerCell = -1
    println(board.tutorialBoard)
    while(true) {
        print("Which cell number do you want to mark? ")
        inputCell = scanner.nextInt() - 1
        while(board.cells[inputCell].state != CellState.EMPTY) {
            print("Cell ${inputCell + 1} has already been chosen. Please pick a new cell ")
            inputCell = scanner.nextInt() - 1
        }
        board.cells[inputCell].state = CellState.CROSS
        if(board.isFull() || !(board.winState()==CellState.EMPTY)) break
        computerCell = inputCell
        while((board.cells[computerCell].state != CellState.EMPTY)) computerCell = random.nextInt(9)
        board.cells[computerCell].state = CellState.CIRCLE
        if(board.isFull() || !(board.winState()==CellState.EMPTY)) break
        println(board)
    }
    println(board)
    when(board.winState()) {
        CellState.EMPTY -> println("This game was a draw")
        CellState.CIRCLE -> println("You have lost the game")
        CellState.CROSS -> println("You have won the game")
    }
}
private enum class CellState(val string: String, val value: Int) {
    EMPTY(" ", 0), CIRCLE("O", 1), CROSS("X", 2)
}
private data class Cell(var state: CellState = CellState.EMPTY){
    fun equals(other: Cell): Boolean = this.state == other.state
}
private class Board {
    val cells = arrayOf(Cell(), Cell(), Cell(), Cell(), Cell(), Cell(), Cell(), Cell(), Cell())
    override fun toString(): String =
        " " + cells[0].state.string + " | " + cells[1].state.string + " | " + cells[2].state.string + "\n" + 
        "-----------" + "\n" +
        " " + cells[3].state.string + " | " + cells[4].state.string + " | " + cells[5].state.string + "\n" + 
        "-----------" + "\n" +
        " " + cells[6].state.string + " | " + cells[7].state.string + " | " + cells[8].state.string + "\n"
    val tutorialBoard =
        " " + "1" + " | " + "2" + " | " + "3" + "\n" +
        "-----------" + "\n" +
        " " + "4" + " | " + "5" + " | " + "6" + "\n" +
        "-----------" + "\n" +
        " " + "7" + " | " + "8" + " | " + "9" + "\n"
    fun winState(): CellState {
        if((cells[0].equals(cells[1]) && cells[0].equals(cells[2])) || (cells[0].equals(cells[3]) && cells[0].equals(cells[6])) || (cells[0].equals(cells[4]) && cells[0].equals(cells[8]))) return cells[0].state
        if(cells[1].equals(cells[4]) && cells[1].equals(cells[7])) return cells[1].state
        if((cells[2].equals(cells[4]) && cells[2].equals(cells[6])) || (cells[2].equals(cells[5]) && cells[2].equals(cells[8]))) return cells[2].state
        if(cells[6].equals(cells[7]) && cells[6].equals(cells[8])) return cells[6].state
        if(cells[3].equals(cells[4]) && cells[3].equals(cells[5])) return cells[3].state
        return CellState.EMPTY
    }
    fun isFull(): Boolean {
        for(cell in cells) if(cell.state.value==0) return false
        return true
    }
}