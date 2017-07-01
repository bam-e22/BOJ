import Foundation

let input : String = readLine()!;

var idx : Int = 0

while idx + 10 < input.characters.count {
    
    var startIdx = input.index(input.startIndex, offsetBy: idx)
    var endIdx = input.index(input.startIndex, offsetBy: idx + 10)
    
    let range = startIdx ..< endIdx
    print(input.substring(with: range))
    
    idx += 10
}

var startIdx = input.index(input.startIndex, offsetBy: idx)
var endIdx = input.endIndex

let range = startIdx ..< endIdx
print(input.substring(with: range))





