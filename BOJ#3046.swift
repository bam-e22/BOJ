import Foundation

let inputArr = readLine()!.characters.split(separator: " ").map({Int(String($0))!})

let R1 : Int = inputArr[0]
let S : Int = inputArr[1]

print(2 * inputArr[1] - inputArr[0])
