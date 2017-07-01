import Foundation

let input = readLine()!
let inputArr = input.characters.split(separator: " ").map({Int(String($0))!})

var minDistance : Int = Int.max

minDistance = min(minDistance, inputArr[0] - 0)
minDistance = min(minDistance, inputArr[2] - inputArr[0])
minDistance = min(minDistance, inputArr[3] - inputArr[1])
minDistance = min(minDistance, inputArr[1] - 0)

print(minDistance)
