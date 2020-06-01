module.exports = (sequelize, DataTypes) => {
    return sequelize.define('comment', {

        comment_id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            allowNull: false
        },
        writer: {
            type: DataTypes.STRING(45),
            allowNull: false
        }
    },{
        timestamps: false,
        tableName: 'comment'
    })
}