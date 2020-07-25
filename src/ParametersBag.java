public class ParametersBag
{
    public String path;
    public long limit;

    public ParametersBag(String args[])
    {
        if (args.length != 4){
            throw new IllegalArgumentException("-p путь к папке -l лимит");
        }
        if (args[0].equals("-p")){
            this.path = args[1];
        }
        else {
            throw new IllegalArgumentException("Не указан путь к папке");
        }
        if (args[2].equals("-l")){
            this.limit = SizeCalculator.getSizeFromHumanReadable(args[3]);
        }
        else {
            throw new IllegalArgumentException("Не указан путь к папке");
        }
    }

    public long getLimit()
    {
        return limit;
    }

    public String getPath()
    {
        return path;
    }
}