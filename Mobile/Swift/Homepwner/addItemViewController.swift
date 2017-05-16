//
//  addItemViewController.swift
//  Homepwner
//
//  Created by ebony cross on 12/5/16.
//  Copyright © 2016 ebony cross. All rights reserved.
//

import Foundation
import UIKit

class addItemViewController: UIViewController {
    var name: String = ""
    @IBOutlet var txtFld: UITextField!
    var itemStore: ItemStore!
    
    
    
    @IBAction func addNewItem(sender: AnyObject){
        //must add a new Item first to the store, create a new item and add it to the store
        //let newItem = itemStore.addItem(name)
        
        //figure out where that item is in the array
        //if let index = itemStore.allItems.indexOf(newItem){
        //    let indexPath = NSIndexPath(forRow: index, inSection: 0)
            
            //insert this new row into the table
            //tableView.insertRowsAtIndexPaths([indexPath], withRowAnimation: .Automatic)
        }
    }
    
    
    

