module.exports = (sequelize, DataTypes) => {
    return sequelize.define('board', {

        board_id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            allowNull: false
        },
        type: {
            type: DataTypes.STRING(45),
            allowNull: false
        },
        title: {
            type: DataTypes.STRING(45),
            allowNull: false
        },
        contents: {
            type: DataTypes.STRING(6000),
            allowNull: false
        },
        user_id: {
            type: DataTypes.STRING(10),
            allowNull: false
        },
        good: {
            type: DataTypes.INTEGER,
            allowNull: false
        },
        time: {
            type: DataTypes.STRING(45),
            allowNull: false
        }
    },{
        timestamps: false,
        tableName: 'board'
    })
}