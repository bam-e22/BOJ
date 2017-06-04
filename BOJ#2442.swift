import Foundation

let N : Int = Int(readLine()!)!

var line : String = ""

for i in 0..<N {
    
    // 공백 출력
    for j in 0..<(N - i - 1) {
        
        line += " "
    }
    
    // 별 출력
    for j in 0..<(2 * i + 1) {
        
        line += "*"
    }
    
    line += "\n"
}

print(line)
