//
//  ItemsViewController.swift
//  Homepwner
//
//  Created by ebony cross on 9/19/16.
//  Copyright © 2016 ebony cross. All rights reserved.
//

//import Foundation
import UIKit

class ItemsViewController: UITableViewController {
    var itemStore: ItemStore!
    
       @IBAction func addNewItem(sender: AnyObject){
        //must add a new Item first to the store, create a new item and add it to the store
        let newItem = itemStore.createItem()
        
        //figure out where that item is in the array
        if let index = itemStore.allItems.indexOf(newItem){
            let indexPath = NSIndexPath(forRow: index, inSection: 0)
            
            //insert this new row into the table 
            tableView.insertRowsAtIndexPaths([indexPath], withRowAnimation: .Automatic)
        }
    }
    
    @IBAction func toggleEditingMode(sender: AnyObject){
        //if you are currently in editing mode
        if editing{
            //change text of button to inform user of state
            sender.setTitle("Edit", forState: .Normal)
            
            //turn off editing mode
            setEditing(false, animated: true)
        }
        else{
            //change text of button to inform user of state 
            sender.setTitle("Done", forState: .Normal)
            
            //Enter editing mode
            setEditing(true, animated: true)
        }
    
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //get the height of the status bar 
        let statusBarHeight = UIApplication.sharedApplication().statusBarFrame.height
        
        let insets = UIEdgeInsets(top: statusBarHeight, left: 0, bottom: 0, right: 0)
        tableView.contentInset = insets
        tableView.scrollIndicatorInsets = insets
    }
   
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int)->Int{
        return itemStore.allItems.count
    }
    
     //deletion with function to create user alerts for deletion of an item
    override func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
        //if the table view is asking to commit a delete command...
        if editingStyle == .Delete{
            let item = itemStore.allItems[indexPath.row]
            
            let title = "Delete \(item.name)"
            let message = "Are you sure you want to delete this item?"
            
            let ac = UIAlertController(title: title,
                message: message,
                preferredStyle: .ActionSheet)
            
            let cancelAction = UIAlertAction(title: "Cancel", style: .Cancel, handler: nil)
            
            ac.addAction(cancelAction)
            
            let deleteAction = UIAlertAction(title: "Delete", style: .Destructive,
                                             handler: {(action:UIAlertAction) -> Void in
                
                    //remove the item from the store
                    self.itemStore.removeItem(item)
                    
                    //also remove that row from the table view with an animation
                    self.tableView.deleteRowsAtIndexPaths([indexPath], withRowAnimation: .Automatic)
            })
            
            ac.addAction(deleteAction)
            
            //present the alert controller
            //presentViewController(ac, animated: true, completion: nil)
            self.presentViewController(ac, animated: true, completion: nil)
            
            //remove the item from the store
            //itemStore.removeItem(item)
            
            //also remove that row from the table view with an animation
            //tableView.deleteRowsAtIndexPaths([indexPath], withRowAnimation: .Automatic)
        }
    }

    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath)-> UITableViewCell{
        //create an instance of UITableViewCell, with default appearance
        
        //let cell = UITableViewCell(style: .Value1, reuseIdentifier: "UITableViewCell")
        //get a new or recycled cell
        let cell = tableView.dequeueReusableCellWithIdentifier("UITableViewCell", forIndexPath: indexPath)
       
        
        //set the ttext on the cell with the description of the item
        //that is at the nth index of items, where n = row this cell
        //will appear in on the tableview
        let num = indexPath.row
        let item = itemStore.allItems[num]
        
        cell.textLabel?.text = item.name
        cell.detailTextLabel?.text = "$\(item.valueInDollars)"
        
        return cell
    }
    
    override func tableView(tableView: UITableView, moveRowAtIndexPath sourceIndexPath: NSIndexPath, toIndexPath destinationIndexPath: NSIndexPath) {
        //update the model
        itemStore.moveItemAtIndex(sourceIndexPath.row, toIndex: destinationIndexPath.row)
    }
    
   
    



}
