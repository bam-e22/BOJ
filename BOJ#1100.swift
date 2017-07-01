import Foundation

var count : Int = 0

for row in 0..<8 {
	
	var inputLine = readLine()!.characters.map({ String($0) })
	
	for col in 0..<8 {
		
		if ((row % 2 == 0 && col % 2 == 0) || (row % 2 != 0 && col % 2 != 0)) && inputLine[col] == "F" {
			
			count = count + 1
		}
	}
}

print(count)



