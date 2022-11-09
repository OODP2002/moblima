public class AgeRating implements MovieInformation<AgeEnum> {
    
    private AgeEnum ageEnum;

    public AgeRating() {
        this.ageEnum = AgeEnum.G;
    }

    public AgeRating(AgeEnum ageEnum) {
        this.ageEnum = ageEnum;
    }

    public String getDetailString() {
        switch (this.ageEnum) {
            case G:
                return "G";
            case PG:
                return "PG";
            case PG13:
                return "PG13";
            case NC16:
                return "NC16";
            case M18: 
                return "M18";
            case R21: 
                return "R21";
            default:
                return "G";
        }
    }

    public AgeEnum getDetail() {
        return this.ageEnum;
    }

    public void setDetail(AgeEnum ageEnum) {
        this.ageEnum = ageEnum;
    }
}