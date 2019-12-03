class Division implements ArithmeticOperation {
    public float function(float a, float b){
        if(0 == b){
            return 0;
        }
        return a/b;
    }
}