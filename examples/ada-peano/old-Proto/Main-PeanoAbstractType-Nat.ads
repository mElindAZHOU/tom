with Main.PeanoAbstractType ; use Main.PeanoAbstractType ;
with VisitablePackage, ObjectPack; use ObjectPack;

package Main.PeanoAbstractType.Nat is 

	type Nat is abstract new PeanoAbstractType with record
	hashCode: integer;
	end record;


	type NatPtr is access all Nat'Class;
	type NatPtrArray is array (Natural range <>) of NatPtr;
	type NatPtrArrayPtr is access all ObjectPtrArray;


	function iszero(this: Nat'Class) return boolean;

	function issuc(this: Nat'Class) return boolean;

	function isplus(this: Nat'Class) return boolean;

	function getpred(this: Nat'Class) return Nat'Class ;

	function setpred(this: Nat'Class; arg: Nat'Class) return Nat'Class ;

	function getx1(this: Nat'Class) return Nat'Class ;

	function setx1(this: Nat'Class; arg: Nat'Class) return Nat'Class ;

	function getx2(this: Nat'Class) return Nat'Class ;

	function setx2(this: Nat'Class; arg: Nat'Class) return Nat'Class ;

	function length(this: Nat'Class) return Integer ;

	function revers(this: Nat'Class) return Nat'Class ;

	
end Main.PeanoAbstractType.Nat;


