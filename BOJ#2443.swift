import Foundation

let N = Int(readLine()!)!

var line : String = ""

for i in 0..<N {
    
    // 공백
    for j in 0..<i {
        
        line += " "
    }
    
    // 별
    for j in 0..<2*(N-i)-1 {
        
        line += "*"
    }
    
    line += "\n"
}

print(line)
