import {Component, OnInit} from '@angular/core';
import {OwnerService} from '../../service/owner.service';
import {Owner} from '../../dto/owner';

@Component({
  selector: 'app-owner',
  templateUrl: './owner.component.html',
  styleUrls: ['./owner.component.scss']
})
export class OwnerComponent implements OnInit {

  error = false;
  errorMessage = '';
  owners: Owner[] = [];
  owner: Owner;
  // exampleOwner: Owner = new Owner('Ownername',1,new Date(),'paint','image');
  // exampleOwners: Owner[] = [this.exampleOwner,this.exampleOwner,this.exampleOwner,this.exampleOwner,this.exampleOwner];


  constructor(private ownerService: OwnerService) {
  }

  ngOnInit() {
    this.loadOwner(1);
    this.loadAllOwnersFiltered(null);
  }

  // onClickSearchFiltered(owner: Owner):void{
  //   this.loadAllOwnersFiltered(owner);
  // }

  onClickPostOwner(owner: Owner): void {
    // this.owners.push(owner);
    this.owners.push(owner);
    this.postNewOwner(owner);
  }

  // onClickDeleteOwner(id:number):void{
  //   this.deleteOwner(id);
  // }

  /**
   * Error flag will be deactivated, which clears the error message
   */
  vanishError() {
    this.error = false;
  }

  /**
   * Loads the owner for the specified id
   * @param id the id of the owner
   */
  private loadOwner(id: number) {
    this.ownerService.getOwnerById(id).subscribe(
      (owner: Owner) => {
        this.owner = owner;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  // US-1
  private postNewOwner(owner: Owner): void {
    this.ownerService.postOwner(owner).subscribe(
      data => console.log('Success posting!', data),
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  // US-4
  private deleteOwner(id:number):void{
    this.ownerService
      .deleteOwner(id)
      .subscribe();
  }

  // US-5
  private loadAllOwnersFiltered(owner: Owner): void {
    if (owner == null) {
      this.ownerService.getAllOwners().subscribe(
        owners => this.owners = owners);
    } else {
      this.ownerService.getAllOwnersFiltered(owner).subscribe(
        owners => this.owners = owners);
    }
  }


  private defaultServiceErrorHandling(error: any) {
    console.log(error);
    this.error = true;
    if (error.status === 0) {
      // If status is 0, the backend is probably down
      this.errorMessage = 'The backend seems not to be reachable';
    } else if (error.error.message === 'No message available') {
      // If no detailed error message is provided, fall back to the simple error name
      this.errorMessage = error.error.error;
    } else {
      this.errorMessage = error.error.message;
    }
  }

}
