import Foundation

let input = readLine()!
var inputArr = input.characters.split(separator: " ").map({Int(String($0))!})

inputArr = inputArr.sorted()
print(inputArr[1])

