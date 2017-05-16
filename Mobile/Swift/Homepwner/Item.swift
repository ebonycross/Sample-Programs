//
//  Item.swift
//  Homepwner
//
//  Created by ebony cross on 9/19/16.
//  Copyright © 2016 ebony cross. All rights reserved.
//

//import Foundation
import UIKit

class Item: NSObject{
    var name: String;
    var valueInDollars: Int
    var serialNumber: String?
    let dateCreated: NSDate
    
    init(name:String, serialNumber: String?, valueInDollars: Int){
        self.name = name
        self.valueInDollars = valueInDollars
        self.serialNumber = serialNumber
        self.dateCreated = NSDate()
        
        super.init()
        
    }
    
    convenience init(random: Bool = false) {
        
        if random{
            let adjectives = ["John", "Rusty", "Becky"]
            let nouns = ["Johnson", "Walters", "Denver"]
            
            var idx = arc4random_uniform(UInt32(adjectives.count))
            let randomAdjective = adjectives[Int(idx)]
            
            idx = arc4random_uniform(UInt32(nouns.count))
            let randomNoun = nouns[Int(idx)]
            
            let randomName = "\(randomAdjective) \(randomNoun)"
            let randomValue = Int(arc4random_uniform(100))
            let randomSerialName = NSUUID().UUIDString.componentsSeparatedByString("-").first!
            
            self.init(name:randomName, serialNumber: randomSerialName, valueInDollars: randomValue)
            
        }
        else{
            let randomValue = Int(arc4random_uniform(100))
            self.init(name: "", serialNumber: nil, valueInDollars: randomValue)
            //self.init(name: "", serialNumber: nil, valueInDollars: 0)
        }
    }

}
