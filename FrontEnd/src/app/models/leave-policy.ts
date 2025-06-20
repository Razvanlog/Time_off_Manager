// Path: FrontEnd/src/app/models/leave-policy.ts

export interface LeavePolicy {
  id: number;
  leaveType: 'VACATION' | 'SICK';
  daysPerYear: number;
}
