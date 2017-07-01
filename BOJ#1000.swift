import Foundation

let input : String = readLine()!;
let inputArr = input.characters.split(separator: " ").map({Int(String($0))!})

var sum = 0

for number in inputArr {

sum += number
}

print(sum)
